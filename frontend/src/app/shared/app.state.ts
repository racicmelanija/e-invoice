import { Injectable } from "@angular/core";
import { Action, Selector, State, StateContext } from "@ngxs/store";
import { SetCompany } from "./app.actions";


export interface AppStateModel {
    companyId: string;
    companyName: string;
    role: string;
}

@State<AppStateModel>({
    name: 'app'
})
@Injectable() 
export class AppState {
    
    @Action(SetCompany) 
    setCompany({ patchState }: StateContext<AppStateModel>, { payload }: SetCompany) {
        patchState({ 
            companyId: payload.companyId,
            companyName: payload.companyName,
            role: payload.role
        })
    }

    @Selector()
    static getCompanyId(state: AppStateModel) {
        return state.companyId;
    }

    @Selector()
    static getRole(state: AppStateModel) {
        return state.role;
    }
}