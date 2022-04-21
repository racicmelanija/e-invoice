export class User {
    public id: string;
    public email: string;
    public firstName: string;
    public lastName: string;
    public roles: [];

    constructor(id: string, email: string, firstName: string, lastName: string, roles: []){
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }
}