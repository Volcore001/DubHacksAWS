import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.net.URI;

// import com.google.gson.Gson;
// import com.google.gson.GsonBuilder;
import java.util.Map;

import javax.tools.*;

public class Handler implements RequestHandler<Map<String, String>, String> {
    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        StringBuffer sourceCode = new StringBuffer();
        sourceCode.append("public class HelloClass {\n");
        sourceCode.append("   public String hello() { return \"hello\"; }");
        sourceCode.append("}");

        InMemoryJavaCompiler imjc = new InMemoryJavaCompiler();
        try {
            Class<?> helloClass = imjc.compile("HelloClass", sourceCode.toString());
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "ok";
    }

    

}

class JavaSourceFromString extends SimpleJavaFileObject {
    final String code;
  
    JavaSourceFromString(String name, String code) {
      super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
      this.code = code;
    }
}