import java.lang.instrument.Instrumentation;

public class Agent {
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("Starting the agent");
		Transformer transformer = new Transformer();
		inst.addTransformer(transformer);
	}
}
