package untils;

import chenangel.graduationdesign.generator.model.Book;

import java.util.*;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

public class Kmaens {
    class Point{
        Double x;
        Double y;
        Integer book_id;
        Double distance;

        public Point(Double x,Double y,Integer book_id){
            this.x = x;
            this.y = y;
            this.book_id = book_id;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }
    }
    public List<Integer> getPopularBookId(List<Book> books, Long allacount, Map<String,Integer> typemap){
        List<Point> pointList = new LinkedList<>();
        for (Book book:books) {
            String type = book.getType();
            Double xfenmu = typemap.get(type).doubleValue();
            Double yfenmu = allacount.doubleValue();
            Double x = book.getBorrowacount()/xfenmu;
            Double y = book.getBorrowacount()/yfenmu;
            Point point = new Point(x,y,book.getId());
            pointList.add(point);
        }
        return this.get10LeastDistanPoint(pointList);
    }

    private List<Integer> get10LeastDistanPoint(List<Point> points){
        Integer count = 0;
        List<Integer> integers = new LinkedList<>();
        for (Point p:points) {
            Double distance = Math.sqrt(Math.pow(1-p.x,2.0)+Math.pow(1-p.y,2.0));
            p.setDistance(distance);
        }
        Collections.sort(points,new MyCompartor());
        for(Point p:points){
            if (count>=10||count>=points.size()) {
                break;
            }
            integers.add(p.book_id);
        }
        return integers;
    }
    class MyCompartor implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            Point point1 = (Point)o1;
            Point point2 = (Point)o2;
            return point1.getDistance().compareTo(point2.getDistance());
        }
    }

}
