package Tour;

public class Destinations {
    private String name;
    private int price;
    private String location;
    private String description;
    private String food;
    private String transport;
    private String famousPlace;
    public Destinations(String name, String location, String description, int price, String food, String transport, String famousPlace){
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = price;
        this.food = food;
        this.famousPlace = famousPlace;
        this.transport = transport;

    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getPlaces() {
        return famousPlace;
    }

    public void setPlaces(String famousPlace) {
        this.famousPlace = famousPlace;
    }

    public void setName(String name){
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)==1 || name.charAt(i)==2|| name.charAt(i)==3|| name.charAt(i)==4|| name.charAt(i)==5|| name.charAt(i)==6|| name.charAt(i)==7|| name.charAt(i)==8|| name.charAt(i)==9){
                throw new IllegalArgumentException("Name cannot contain a digit");
            }
            else
                this.name = name;
        }
    }
    public String getName(){
        return name;
    }
    public void setPrice(int price){this.price = price;}
    public int getPrice(){return price;}
}
class Lahore extends Destinations{
    public Lahore(String name,String location,String description,int price,String food, String transport , String famousPlace){
        super(name,location,description,price,food,transport,famousPlace);
    }
}
class Karachi extends Destinations{
    public Karachi(String name,String location,String description,int price,String food, String transport , String famousPlace){
        super(name,location,description,price,food,transport,famousPlace);
    }
}
class Islamabad extends Destinations{
    public Islamabad(String name,String location,String description,int price,String food, String transport , String famousPlace){
        super(name,location,description,price,food,transport,famousPlace);
    }
}
class Murree extends Destinations{
    public Murree(String name,String location,String description,int price,String food, String transport , String famousPlace){
        super(name,location,description,price,food,transport,famousPlace);
    }
}

