package point3d;

public final class Point3D
{
    private final double x;
    private final double y;
    private final double z;


    public Point3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    private Point3D(){this(0, 0, 0);}


    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }


    private double r;
    private double theta;
    private double phi;

    public double getR() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double getTheta() {
        return Math.acos(z / getR());
    }

    public double getPhi() {
        return Math.atan2(y, x);
    }



    public static Point3D fromPolar(double r, double theta, double phi)
    {

        double x = r * Math.sin(theta) * Math.cos(phi);
        double y = r * Math.sin(theta) * Math.sin(phi);
        double z = r * Math.cos(theta);
        return new Point3D(x, y, z);
    }

    public static Point3D fromDec(double x, double y, double z)
    {
        return new Point3D(x, y, z);
    }



    public double distanceTo(Point3D other)
    {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        double dz = other.z - this.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }


    public double distanceToOrigin()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }


    public Point3D move(double dx, double dy, double dz)
    {
        return new Point3D(x + dx, y + dy, z + dz);
    }


    public Point3D scale(double factor)
    {
        return new Point3D(x * factor, y * factor, z * factor);
    }


    public boolean equals(Point3D other, double precision)
    {

        return Math.abs(this.x - other.x) < precision &&
                Math.abs(this.y - other.y) < precision &&
                Math.abs(this.z - other.z) < precision;
    }

    @Override
    public String toString()
    {
        return String.format("point3d.Point3D(x=%.2f, y=%.2f, z=%.2f)", x, y, z);
    }
}






