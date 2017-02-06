import java.util.ArrayList;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PrintMessageMethodVisitor extends MethodVisitor {
	private boolean isImportant = false;
	private String methodName;
	ArrayList<String> parameterIndexes;

	public PrintMessageMethodVisitor(MethodVisitor mv, String name, String className) {
		super(Opcodes.ASM5, mv);
		methodName = name;
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
		// if annotation present, add logging to beginning of the method
		System.out.println(methodName);
		if (isImportant) {
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
			mv.visitLdcInsn(parameterIndexes.toString());
			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
			/*
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", 
			        "out","Ljava/io/PrintStream;");
			mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
			mv.visitInsn(Opcodes.DUP);
			
			mv.visitLdcInsn("A call was made to method \"");
		    mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
		        "java/lang/StringBuilder", "",
		        "(Ljava/lang/String;)V", false);
		    mv.visitLdcInsn(methodName);
		    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
		        "java/lang/StringBuilder", "append",
		        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
		    */
		}
	}
}
