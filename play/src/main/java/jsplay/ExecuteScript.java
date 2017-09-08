package jsplay;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

public class ExecuteScript {
  public static void main(String[] args) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("js");
    try {
      String path = ExecuteScript.class.getResource("/").getPath();
      System.out.println(path);
      engine.eval(new FileReader(path + "JavaScriptMethods.js"));
      if (engine instanceof Invocable) {
        Invocable invocable = (Invocable) engine;
        Methods executeMethod = invocable.getInterface(Methods.class);
        System.out.println(executeMethod.execute("hello", "world"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}