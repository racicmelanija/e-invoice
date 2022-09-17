import { Employment } from "../models/employment.model";

export class SetCompany {
    static readonly type = '[app] set company';
    constructor(public payload: Employment) {}
}