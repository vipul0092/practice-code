package code.vipul.numbers;

/**
 * Created by vgaur created on 23/12/22
 * Class to represent a fraction and having methods to do algebraic operations on it
 */
public class Fraction {
    private final long n;
    private final long d;

    public Fraction(long num) {
        this.n = num;
        this.d = 1;
    }

    public Fraction(long n, long d) {
        this.n = n;
        this.d = d;
    }

    public boolean isFraction() {
        return d != 1;
    }

    public Fraction add(Fraction f) {
        long resd = f.d * this.d;
        long resn = (f.n * this.d) + (this.n * f.d);
        if (resd < 0) {
            resn = -resn;
            resd = -resd;
        }
        return new Fraction(resn, resd);
    }

    public Fraction subtract(Fraction f) {
        long resd = f.d * this.d;
        long resn = (this.n * f.d) - (f.n * this.d);
        if (resd < 0) {
            resn = -resn;
            resd = -resd;
        }
        return new Fraction(resn, resd);
    }

    public Fraction multiply(Fraction f) {
        f = f.normalize();
        long n1 = this.n;
        long n2 = f.n;
        long d1 = this.d;
        long d2 = f.d;

        if (n1 % d2 == 0) {
            n1 = n1 / d2;
            d2 = 1;
        } else if (d2 % n1 == 0) {
            d2 = d2 / n1;
            n1 = 1;
        }

        if (n2 % d1 == 0) {
            n2 = n2 / d1;
            d1 = 1;
        } else if (d1 % n2 == 0) {
            d1 = d1 / n2;
            n2 = 1;
        }

        long resd = d1 * d2;
        long resn = n1 * n2;
        if (resd < 0) {
            resn = -resn;
            resd = -resd;
        }
        return new Fraction(resn, resd).normalize();
    }

    public Fraction divide(Fraction f) {
        return multiply(new Fraction(f.d, f.n));
    }

    public Fraction normalize() {
        long newn = this.n % this.d == 0 ? this.n / this.d : this.n;
        long newd = this.n % this.d == 0 ? 1 : this.d;

        long _gcd = gcd(Math.abs(newn), Math.abs(newd));
        if (_gcd > 1) {
            newn /= _gcd;
            newd /= _gcd;
        }
        return new Fraction(newn, newd);
    }

    public static long gcd(long a, long b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    @Override
    public String toString() {
        return d == 1 ? String.valueOf(n) : String.format("%s/%s", n, d);
    }
}
