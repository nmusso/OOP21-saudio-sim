package model.listener.plugin;

import java.lang.reflect.Method;

import io.github.classgraph.*;

final class TestReflection {

    private TestReflection() { };

    public static void main(final String[] args) {

        final Plugin plugin = new DropplerPlugin();


        Method m;
        Object s;

        try {
            m = plugin.getClass().getMethod("setParameters", Parameters.class);
            m.invoke(plugin, new Parameters());

            m = plugin.getClass().getMethod("getFloatValue", ParameterType.class);
            s = m.invoke(plugin, ParameterType.DROPPLER_LV);

            System.out.print(s + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } 




        try (ScanResult scanResult = new ClassGraph().enableAllInfo().acceptPackages("model.listener.plugin").scan()) {
                final ClassInfo form = scanResult.getClassInfo("model.listener.plugin.DropplerPlugin");
                if (form != null) {
                        final MethodInfoList formMethods = form.getMethodInfo();
                        for (final MethodInfo mi : formMethods) {
                                String methodName = mi.getName();
                                MethodParameterInfo[] mpi = mi.getParameterInfo();
                                for (int i = 0; i < mpi.length; i++) {
                                        String parameterName = mpi[i].getName();
                                        TypeSignature parameterType = mpi[i].getTypeSignatureOrTypeDescriptor();
                                        System.out.println(parameterName + " " + parameterType);
                                }
                        }
                        final FieldInfoList formFields = form.getFieldInfo();
                        for (final FieldInfo fi : formFields) {
                                //String fieldName = fi.getName();

                                System.out.print("\n" + fi.getTypeSignatureOrTypeDescriptor());
                        }

                }
        }

    }

}
