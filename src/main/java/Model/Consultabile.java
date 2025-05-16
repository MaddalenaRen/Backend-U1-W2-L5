package Model;

public abstract class Consultabile {
    private String isbn;
    private String titolo;
    private int anno;
    private int pagine;

    public Consultabile(String isbn, String titolo, int anno, int pagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.anno = anno;
        this.pagine = pagine;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getPagine() {
        return pagine;
    }

    public void setPagine(int pagine) {
        this.pagine = pagine;
    }

  public abstract String getTipo();

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass() != obj.getClass()) return false;
        Consultabile that =(Consultabile) obj;
        return this.isbn.equals(that.isbn);

    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return getTipo() + " - ISBN: " + isbn + ", Titolo: " + titolo +
                ", Anno: " + anno + ", Pagine: " + pagine;
    }
}
