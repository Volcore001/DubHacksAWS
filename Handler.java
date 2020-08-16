import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;
import java.io.IOException;

// import com.google.gson.Gson;
// import com.google.gson.GsonBuilder;
import java.util.Map;

import javax.tools.*;

public class Handler implements RequestHandler<Map<String, String>, String> {
    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        StringBuffer sourceCode = new StringBuffer();
        sourceCode.append("public class HelloClass {\n");
        sourceCode.append("  public static void main(String args[]) {");
        sourceCode.append("    System.out.println(\"This is in another java file\");");
        //sourceCode.append("  }");
        sourceCode.append("}");
        InMemoryJavaCompiler imjc = new InMemoryJavaCompiler();
        try {
            Class<?> helloClass = imjc.compile("HelloClass", sourceCode.toString());   
                return "okok";

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "nope lol";
        }
    }

    public static void main(String[] args) {
        StringBuffer sourceCode = new StringBuffer();
        sourceCode.append("public class HelloClass {\n");
        sourceCode.append("  public static void main(String args[]) {");
        sourceCode.append("    System.out.println(\"This is in another java file\");");
        sourceCode.append("  }");
        sourceCode.append("}");
        InMemoryJavaCompiler imjc = new InMemoryJavaCompiler();
        try {
            Class<?> helloClass = imjc.compile("HelloClass", sourceCode.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

class JavaSourceFromString extends SimpleJavaFileObject {
    final String code;
  
    JavaSourceFromString(String name, String code) {
      super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
      this.code = code;
    }
}