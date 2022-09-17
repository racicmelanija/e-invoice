export class Employment {
    companyId: string;
    companyName: string;
    role: string;

    constructor(companyId: string, companyName: string, role: string) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.role = role;
    }
}