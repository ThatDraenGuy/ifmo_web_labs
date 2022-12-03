import {LoginResponse, User} from "../services/auth";
import {createSlice, PayloadAction} from "@reduxjs/toolkit";

export interface AuthState {
    token: string,
    user: User
}

const initialState: AuthState = {
    token: undefined,
    user: undefined
}

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        setAuth: ((state, action: PayloadAction<LoginResponse>) => {
            state.token = action.payload.token;
            state.user = action.payload.user
        })
    }
})

export const {setAuth} = authSlice.actions

export default authSlice.reducer