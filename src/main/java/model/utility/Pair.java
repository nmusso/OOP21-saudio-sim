package model.utility;

public class Pair<X, Y> {

<<<<<<< HEAD
    private X x;
    private Y y;
=======
    private final X x;
    private final Y y;
>>>>>>> feat/listener_plugin

    public Pair(final X x, final Y y) {
            super();
            this.x = x;
            this.y = y;
    }

<<<<<<< HEAD
    /**
     * 
     * @return
     */
    public void setX(final X x) {
        this.x = x;
    }

    /**
     * 
     * @return
     */
    public void setY(final Y y) {
        this.y = y;
    }

    /**
     * 
     * @return
     */
=======
>>>>>>> feat/listener_plugin
    public X getX() {
            return x;
    }

<<<<<<< HEAD
    /**
     * 
     * @return
     */
=======
>>>>>>> feat/listener_plugin
    public Y getY() {
            return y;
    }

<<<<<<< HEAD
    /**
     * 
     */
=======
>>>>>>> feat/listener_plugin
    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((x == null) ? 0 : x.hashCode());
            result = prime * result + ((y == null) ? 0 : y.hashCode());
            return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            Pair other = (Pair) obj;
            if (x == null) {
                    if (other.x != null)
                            return false;
            } else if (!x.equals(other.x))
                    return false;
            if (y == null) {
                    if (other.y != null)
                            return false;
            } else if (!y.equals(other.y))
                    return false;
            return true;
    }

    @Override
    public String toString() {
<<<<<<< HEAD
            return "Pair [x=" + x + ", y=" + y + "]";
    }



=======
            return "x: " + x + "; y: " + y;
    }


>>>>>>> feat/listener_plugin
}
