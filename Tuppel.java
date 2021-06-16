class Tuppel{
    int x,y;

    public Tuppel(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int hentX(){
        return x;
    }

    public int hentY(){
        return y;
    }

    @Override
    public String toString(){
        return ("Kordinater: " + hentX() + ", " + hentY());
    }
}
