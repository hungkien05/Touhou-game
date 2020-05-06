package game;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void add(double x, double y){
        this.x +=x;
        this.y +=y;
    }

    public void substract(double x, double y){
        this.x -=x;
        this.y -=y;
    }

    public void scale(double rate){
        this.x *=rate;
        this.y *=rate;
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public Vector2D clone() {
        return new Vector2D(this.x, this.y);
    }
    //lay do dai vector
    public double getLength() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
    // tao ra vector moi cung huong co do dai Length
    public void setLength(double length){
        if (this.getLength()!=0) {
            this.x *= (length / this.getLength());
            this.y *= (length / this.getLength());
        }
    }
    //khoang cach 2 vector
    public double distanceTo(Vector2D other){
        return Math.sqrt( (this.x-other.x)*(this.x-other.x) + (this.y-other.y)*(this.y-other.y) );
    }
    // lay goc giua vector va truc hoanh Ox
    public double getAngle() {
        return Math.atan2(y, x);
    }
    //
    public void setAngle(double angle){
        if (this.getLength()!=0) {
            this.x = this.getLength() * Math.cos(angle);
            this.y = this.getLength() * Math.sin(angle);
        }
    }
    public static void main(String[] args) {
        Vector2D v1 = new Vector2D(3, 3);
        Vector2D v2 = new Vector2D(6, 7);
        System.out.println(v1.getLength() + " " + 3*Math.sqrt(2));

    }
}
