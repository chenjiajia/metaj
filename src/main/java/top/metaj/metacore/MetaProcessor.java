package top.metaj.metacore;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("top.metaj.metacore.Meta")
public class MetaProcessor extends AbstractProcessor {
    TypeElement assignmentElement;
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        Elements elementUtils = env.getElementUtils();
        assignmentElement = elementUtils.getTypeElement("top.metaj.metacore.Meta");
    }
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(assignmentElement);
        for (Element element : elements) {
            processAssignment(element);
        }
        return true;
    }
    private void processAssignment(Element element) {
        Meta meta = element.getAnnotation(Meta.class);
        String[] targets=meta.value();
        for (String targetClass : targets) {
            try{
                Class<?> clz = Class.forName(targetClass);
                MetaHandler handler= (MetaHandler) clz.newInstance();
                handler.process(element);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }


}