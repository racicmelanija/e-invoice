export class InvoiceItem {
    public name: string;
    public unitOfMeasure: string;
    public unitPrice: number;
    public quantity: number;
    public discount: number;
    public taxPercent: number;

    constructor(name: string, unitOfMeasure: string, unitPrice: number, quantity: number, discount: number, taxPercent: number) {
        this.name = name;
        this.unitOfMeasure = unitOfMeasure;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
        this.taxPercent = taxPercent;
    }
}