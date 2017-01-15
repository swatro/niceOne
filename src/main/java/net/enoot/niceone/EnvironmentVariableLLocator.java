package net.enoot.niceone;


import java.util.Map;

class EnvironmentVariableLLocator {

    static String getEnvironmentVariable(String environmentVariableKey) {
        Map<String, String> environmentVariables = System.getenv();

        if (environmentVariables.containsKey(environmentVariableKey)) {
            return environmentVariables.get(environmentVariableKey);
        }

        throw new environmentVariableLocatorException("unable to locate environmentVariable for" + environmentVariableKey);
    }


    public static class environmentVariableLocatorException extends RuntimeException {
        environmentVariableLocatorException(String message) {
            super(message);
        }
    }
}
