package aspect;

import aspect.myannotation.Auditable;
import org.springframework.stereotype.Component;

@Component
public class MyService {


	@Auditable(action = "test action")
	public void test(){
		System.out.println("method executed!");
	}
}