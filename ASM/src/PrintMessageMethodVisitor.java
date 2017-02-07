import java.util.ArrayList;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PrintMessageMethodVisitor extends MethodVisitor {
	private boolean isImportant = false;
	ArrayList<String> parameterIndexes;

	public PrintMessageMethodVisitor(MethodVisitor mv, String name, String className) {
		super(Opcodes.ASM5, mv);
		parameterIndexes = new ArrayList<>();
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		if ("LImportantLog;".equals(desc)) {
			isImportant = true;
			return new AnnotationVisitor(Opcodes.ASM5, super.visitAnnotation(desc, visible)) {
				public AnnotationVisitor visitArray(String name) {
					if ("fields".equals(name)) {
						return new AnnotationVisitor(Opcodes.ASM5, super.visitArray(name)) {
							public void visit(String name, Object value) {
								parameterIndexes.add((String) value);
								super.visit(name, value);
							}
						};
					} else {
						return super.visitArray(name);
					}
				}
			};
	    }
	    return super.visitAnnotation(desc, visible);
	}
	
	@Override
	public void visitCode() {
		super.visitCode();
		if (isImportant) {
			for (String index : parameterIndexes) {
				mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
				mv.visitVarInsn(Opcodes.ALOAD, Integer.valueOf(index) + 1);
				mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
			}
		}
	}
}
