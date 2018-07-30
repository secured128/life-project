package life.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static life.utils.FileUtils.getPathToGeneratedClass;

/**
 * Created by alexb on 1/18/2018.
 */
public class EnumGenerator {


    private static final String ENUM_TEMPLATE = "package {0};\n" +
            "\n" +
            "public enum {1} '{'\n" +
            "\n" +
            "{2};\n" +
            "\n" +
            "{3}\n" +
            "\t{1} ({4})'{'\n" +
            "{5}" +
            "\t'}'\n" +
            "\n" +
            "{6}" +
            "\n" +
            "'}'";

    public static void generateEnum(String packageName, String enumName, LinkedHashMap<String, Class> dataAndTypesMap, Map<String, List<Object>> keyValues) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(getPathToGeneratedClass(packageName, enumName)));
        PrintWriter printWriter = new PrintWriter(fileWriter);
        StringBuffer constarctorSb = new StringBuffer();
        StringBuffer enumValuesSb = new StringBuffer();
        StringBuffer enumMemberSb = new StringBuffer();
        StringBuffer enumConstractorSettingsSb = new StringBuffer();
        StringBuffer getters = new StringBuffer();
        dataAndTypesMap.entrySet().stream().forEach(e -> {
            enumMemberSb.append("\tprivate ");
            if (CIRCLE_REFERENCED_ENUM.class.equals(e.getValue())) {
                enumMemberSb.append(e.getKey());
            } else if (e.getValue().isArray()) {
                enumMemberSb.append(e.getValue().getSimpleName());
            } else {
                enumMemberSb.append(e.getValue().getName());
            }
            if (CIRCLE_REFERENCED_ENUM.class.equals(e.getValue())) {
                enumMemberSb.append(" ").append(convertEnumNameToCamelString(e.getKey())).append(";\n");
            } else {
                enumMemberSb.append(" ").append(e.getKey()).append(";\n");
            }

            if (CIRCLE_REFERENCED_ENUM.class.equals(e.getValue())) {
                enumConstractorSettingsSb.append("\t\tthis.").append(convertEnumNameToCamelString(e.getKey())).append(" = ").append(convertEnumNameToCamelString(e.getKey())).append(";\n");
            } else {
                enumConstractorSettingsSb.append("\t\tthis.").append(e.getKey()).append(" = ").append(e.getKey()).append(";\n");
            }
            if (constarctorSb.length() > 0) {
                constarctorSb.append(",");
            }
            if (CIRCLE_REFERENCED_ENUM.class.equals(e.getValue())) {
                constarctorSb.append(e.getKey()).append(" ").append(convertEnumNameToCamelString(e.getKey()));
                getters.append("\n\tpublic ").append(e.getKey()).append(" ").append(convertMemberNameToGetter(convertEnumNameToCamelString(e.getKey()))).append("(){\n\t\treturn ").append("this.").append(convertEnumNameToCamelString(e.getKey())).append(";\n\t};\n");
            } else if (e.getValue().isArray()) {
                constarctorSb.append(e.getValue().getSimpleName()).append("").append(e.getKey());
                getters.append("\n\tpublic ").append(e.getValue().getSimpleName()).append(" ").append(convertMemberNameToGetter(e.getKey())).append("(){\n\t\treturn ").append("this.").append(e.getKey()).append(";\n\t};\n");
            } else {
                constarctorSb.append(e.getValue().getName()).append(" ").append(e.getKey());
                getters.append("\n\tpublic ").append(e.getValue().getName()).append(" ").append(convertMemberNameToGetter(e.getKey())).append("(){\n\t\treturn ").append("this.").append(e.getKey()).append(";\n\t};\n");
            }


        });
        keyValues.entrySet().stream().forEach(e -> {
            if (enumValuesSb.length() > 0) {
                enumValuesSb.append(",\n\t");
            } else {
                enumValuesSb.append("\t");
            }
            enumValuesSb.append(e.getKey());
            if (e.getValue() != null && e.getValue().size() > 0) {
                enumValuesSb.append("(");
                Iterator<Class> objectTypes = dataAndTypesMap.values().iterator();

                StringBuffer enumTempValuesSb = new StringBuffer();
                e.getValue().stream().forEach(v -> {
                    Class currentType = objectTypes.next();
                    if (enumTempValuesSb.length() > 0) {
                        enumTempValuesSb.append(", ");
                    }
                    if (v != null) {
                        if (currentType == String.class) {
                            enumTempValuesSb.append("\"").append(v).append("\"");
                        } else if (CIRCLE_REFERENCED_ENUM.class.equals(currentType)) {
                            enumTempValuesSb.append(v);
                        } else if (currentType.isEnum()) {
                            enumTempValuesSb.append(currentType.getName()).append(".").append(currentType.cast(v));
                        } else if (currentType.isArray()) {
                            enumTempValuesSb.append("new ").append(currentType.getSimpleName()).append(" {");
                            StringBuffer values = new StringBuffer();
                            if (v instanceof Integer[]) {
                                Integer[] intArray = (Integer[]) v;
                                for (int y = 0; y < intArray.length; y++) {
                                    if (y > 0) {
                                        values.append(",");
                                    }
                                    values.append(String.valueOf(intArray[y]));
                                }
                            } else if (v instanceof Float[]) {
                                Float[] intArray = (Float[]) v;
                                for (int y = 0; y < intArray.length; y++) {
                                    if (y > 0) {
                                        values.append(",");
                                    }
                                    values.append(String.valueOf((float) intArray[y])).append("f");
                                }
                            }
                            enumTempValuesSb.append(values.toString()).append("}");
                        } else if (currentType == Float.class) {
                            enumTempValuesSb.append(Float.parseFloat((String) v)).append("f");
                        } else if (CIRCLE_REFERENCED_ENUM.class.equals(currentType)) {
                            enumTempValuesSb.append(v);
                        } else if (currentType == Character.class) {
                            enumTempValuesSb.append("'").append(currentType.cast(v)).append("'");
                        } else {
                            enumTempValuesSb.append(currentType.cast(v));
                        }
                    } else {
                        enumTempValuesSb.append("null");
                    }
                });
                String enumTempValues = enumTempValuesSb.append(")").toString();
                enumValuesSb.append(enumTempValues);
            }
        });
        String enumTempValues = enumValuesSb.toString();
        printWriter.printf(MessageFormat.format(ENUM_TEMPLATE, packageName, enumName, enumTempValues.toString(), enumMemberSb.toString(), constarctorSb.toString(), enumConstractorSettingsSb.toString(), getters.toString()));
        printWriter.close();
    }

    private static String convertEnumNameToCamelString(String enumName) {
        String[] anumNameWithoutPackageArray = enumName.split("\\.");
        String anumNameWithoutPackage = anumNameWithoutPackageArray[anumNameWithoutPackageArray.length - 1];
        String[] stringTokens = anumNameWithoutPackage.toLowerCase().split("_");
        StringBuffer camelName = new StringBuffer();
        for (int i = 0; i < stringTokens.length; i++) {
            camelName.append((i == 0) ? stringTokens[i].substring(0, 1) : stringTokens[i].substring(0, 1).toUpperCase()).append(stringTokens[i].substring(1, stringTokens[i].length()));
        }
        return camelName.toString();
    }

    private static String convertMemberNameToGetter(String memberName) {
        return "get" + Character.toString(memberName.charAt(0)).toUpperCase() + memberName.substring(1, memberName.length());
    }
//
//    public static void main(String[] args) {
//        System.out.println(convertMemberNameToGetter("SIDE_AHAIN_BLASS_CD"));
//    }
}
