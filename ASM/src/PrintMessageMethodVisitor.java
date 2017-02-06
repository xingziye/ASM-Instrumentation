import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PrintMessageMethodVisitor extends MethodVisitor {
	private boolean isAnnotationPresent;

	public PrintMessageMethodVisitor(MethodVisitor mv, String name, String className) {
		super(Opcodes.ASM5, mv);
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		return null;
		// 1. check method for annotation @ImportantLog
	    // 2. if annotation present, then get important method param indexes
	}
	
	@Override
	public void visitCode() {
		// 3. if annotation present, add logging to beginning of the method
	}
}
