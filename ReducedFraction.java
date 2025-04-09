package reducedfractionapp;

import java.util.Objects;

/** Несократимая дробь.
 */
public class ReducedFraction {

    /* =========================== Свойства =============================== */

    /* ---------------------- Числитель и знаменатель --------------------- */
    private int numerator = 1;
    private int denominator;


    /* =========================== Операции ============================== */

    /* ---------------------------- Порождение ---------------------------- */

    /** Создание дроби с указанием ее числителя и знаменателя. **/
    public ReducedFraction(int numerator, int denominator)
    {
        if (denominator < 0)
        {
            this.numerator = numerator * -1;
            this.denominator = denominator * -1;
        }
        else
        {
            this.numerator = numerator;
            this.denominator = denominator;
        }
        if (denominator == 0)
        {
            throw new IllegalArgumentException("Denominator cannot be zero, fix it.");
        }
        reduce();
    }

    /** Создание дроби на основе целого числа. **/
    public ReducedFraction(int number)
    {
        this.numerator = number;
    }

    /* ---------------------------- Доступ к параметрам ---------------------------- */
    /** Вывести значение числителя **/
    public int getNumerator() { return this.numerator; }

    /** Вывести значение знаменателя **/
    public int getDenominator() { return this.denominator; }

    /* --------------------- Арифметические операции ---------------------- */

    /** Сложение двух дробей. **/
    public ReducedFraction add(ReducedFraction other)
    {
        int newDenominator = this.denominator * other.denominator;
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;

        return new ReducedFraction(newNumerator, newDenominator);
    }

    /** Вычитание двух дробей. **/
    public ReducedFraction sub(ReducedFraction other)
    {
        int newDenominator = this.denominator * other.denominator;
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;

        return new ReducedFraction(newNumerator, newDenominator);
    }

    /** Умножение двух дробей. **/
    public ReducedFraction mult(ReducedFraction other)
    {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;

        return new ReducedFraction(newNumerator, newDenominator);
    }


    /** Деление двух дробей. **/
    public ReducedFraction div(ReducedFraction other)
    {
        if (other.numerator == 0)
        {
            throw new IllegalArgumentException("You can't divide on zero");
        }

        int newNumerator = this.numerator * other.denominator;
        int newDenominator = this.denominator * other.numerator;

        return new ReducedFraction(newNumerator, newDenominator);
    }

    /** Сокращение дроби **/

    private int gcd(int numerator, int denominator) // Нахождение НОД
    {
        if (denominator == 0)
        {
            return numerator;
        }
        return gcd(denominator, numerator % denominator);
    }

    private void reduce() // Сокращение дроби
    {
        int gcd = this.gcd(this.numerator, this.denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;

        // Знаменатель должен быть положительным
        if (this.denominator < 0)
        {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
    }
    /* --------------------- Операции сравнения ---------------------- */

    /** Сравнение двух дробей. **/
    public int toCompare(ReducedFraction other)
    {
        // Приводим дроби к общему знаменателю и сравниваем числители
        int left = this.numerator * other.denominator;
        int right = other.numerator * this.denominator;

        return Integer.compare(left, right);
    }

    /** Эквивалентность двух дробей **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReducedFraction that = (ReducedFraction) o;
        return numerator == that.numerator && denominator == that.denominator;
    }



    /* --------------------- Операции преобразования ---------------------- */

    /** Представить как строку. **/
    @Override
    public String toString ()
    {
        if (denominator == 1) // Для целого числа
        {
            return Integer.toString(this.numerator);
        }
        else // Для дроби
        {
            return Integer.toString(this.numerator) + "/" + Integer.toString(this.denominator);
        }
    }

    /** Представить как вещественное число. **/
    public double toDouble()
    {
        return (double) numerator / denominator;
    }
}
