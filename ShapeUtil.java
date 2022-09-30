import java.util.List;

public class ShapeUtil {

    /**
     * cmt.
     */
    public String printInfo(List<GeometricObject> shapes) {
        String a = "Circle:" + "\n";
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i) instanceof Circle) {
                a += shapes.get(i).getInfo() + "\n";
            }
        }
        String b = "Triangle:" + "\n";
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i) instanceof Triangle) {
                b += shapes.get(i).getInfo() + "\n";
            }
        }
        return a + b;
    }
}
