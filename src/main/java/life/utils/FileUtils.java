package life.utils;

import java.io.File;

/**
 * Created by alexb on 2/11/2018.
 */
public class FileUtils {

    public static String getPathToGeneratedClass(Class cls) {
        return getPathToGeneratedClass(cls.getPackage().getName(), cls.getSimpleName());
    }

    public static String getPathToGeneratedClass(String packageName, String className) {
        return getPathToGeneratedFile(packageName, className, "java");
    }

    public static String getPathToGeneratedFile(Class cls, String fileSuffix) {
        return getPathToGeneratedFile(cls.getPackage().getName(), cls.getSimpleName(), fileSuffix);
    }

    public static String getPathToGeneratedFile(String packageName, String className, String fileSuffix) {
        return "." + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + packageName.replace(".", File.separator) + File.separator + className + "." + fileSuffix;
    }

//    public static String getPathToGeneratedResource(Class cls) {
//        return getPathToGeneratedResource(cls.getPackage().getName(),cls.getSimpleName());
//    }
//
//    public static String getPathToGeneratedResource(String packageName, String className) {
//        return "." + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + packageName.replace(".", File.separator) + File.separator + className + ".java";
//    }
}
