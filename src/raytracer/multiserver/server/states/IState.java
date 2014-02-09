package raytracer.multiserver.server.states;

import java.io.PrintWriter;

public interface IState {
	
	public void talk(PrintWriter out);
	public void execute(String serverResponse);
}
