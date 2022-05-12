package com.pisicilesalbatice.ams.Model;

import java.util.Objects;

public class Pair<Left, Right>
{
    private Left left;
    private Right right;

    public Pair(Left left, Right right)
    {
        this.left = left;
        this.right = right;
    }

    public Left getLeft()
    {
        return left;
    }

    public void setLeft(Left left)
    {
        this.left = left;
    }

    public Right getRight()
    {
        return right;
    }

    public void setRight(Right right)
    {
        this.right = right;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(left, right);
    }

    @Override
    public String toString()
    {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
