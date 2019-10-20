package zone.refactor.spring.hateoas.provider;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ParsedPath {
    private final Map<Integer, String> variables;
    private final String[] pathParts;

    ParsedPath(String path) {
        String[] pathParts = path.split("/", -1);
        Map<Integer, String> variables = new HashMap<>();

        for (int i = 0; i < pathParts.length; i++) {
            if (pathParts[i].startsWith("{")) {
                variables.put(i, pathParts[i].replace("{", "").replace("}", ""));
            }
        }
        this.pathParts = pathParts;

        this.variables = variables;
    }

    String render(String basePath, String[] parameters) {
        if (parameters.length != variables.size()) {
            throw new RuntimeException("Invalid path configuration, the number of parameters does not match the variables in the path.");
        }
        List<String> parts = new ArrayList<>();
        int parameterIndex = 0;
        for (String pathPart : pathParts) {
            if (pathPart.startsWith("{")) {
                try {
                    parts.add(URLEncoder.encode(parameters[parameterIndex++], "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            } else {
                parts.add(pathPart);
            }
        }

        return basePath + String.join("/", parts);
    }
}
