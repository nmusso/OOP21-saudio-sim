package model.listener.plugin;

import java.lang.reflect.Method;
import java.util.Map;

import io.github.classgraph.*;



final class TestReflection {

    private TestReflection() { };

    public static void main(final String[] args) {

        final Plugin plugin = new DropplerPlugin();

        Method m;
        Object s = null;

        try {
            m = plugin.getClass().getMethod("setParameters", Parameters.class);
            m.invoke(plugin, new Parameters());

            m = plugin.getClass().getMethod("getDropplerLv");
            s = m.invoke(plugin);

            System.out.println(s.getClass().toString() + " = " + s);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (ScanResult scanResult = new ClassGraph().enableAllInfo().acceptPackages("model.listener.plugin").scan()) {
            final ClassInfo form = scanResult.getClassInfo("model.listener.plugin.DropplerPlugin");
            if (form != null) {
                //final MethodInfoList formMethods = form.getMethodInfo();
                final Map<String, MethodInfoList> mappa = form.getMethodInfo().asMap();
                final var x = mappa.get("getDropplerLv");
                final MethodInfo met1 = x.get(0);
                final TypeSignature retType = met1.getTypeSignatureOrTypeDescriptor().getResultType();
                System.out.println(retType);
                var retValue = Class.forName(retType.toString());
                Float c = (Float) s;
                System.out.println(Float.compare(c, 1.0f) == 0 ? "ok" : "no"); 


            }
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

}
