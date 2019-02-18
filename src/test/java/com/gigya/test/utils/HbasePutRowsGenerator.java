package com.gigya.test.utils;

public class HbasePutRowsGenerator {

    public static void main(String[] args) {
        for (long i = 946677600000L; i < 1548831612105L; i += 10000000) {

            System.out.println("\nput 'HDS-audit_6', 'auditlog|audit|" + i + "', 'content:committed', '0' , " + i);
            System.out.println("put 'HDS-audit_6', 'auditlog|audit|" + i + "', 'content:document', '{\"ttl\":\"6\",\"@timestamp\":" + i + "}' , " + i);
            System.out.println("put 'HDS-audit_6', 'auditlog|audit|" + i + "', 'content:timestamp', '" + i + "' , " + i);
            System.out.println("put 'HDS-audit_6', 'auditlog|audit|" + i + "', 'content:ver', '1' , " + i);
        }

    }

}
