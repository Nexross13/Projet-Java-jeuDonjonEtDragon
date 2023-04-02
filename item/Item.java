package item;

public class Item {
    // Attribut
    private TypeItem typeItem;

    // Constructeur
    public Item(TypeItem typeItem){
        this.typeItem = typeItem;
    }

    // Guetteur
    public TypeItem getTypeItem() {
        return typeItem;
    }
}
