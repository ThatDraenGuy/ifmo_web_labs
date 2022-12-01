import {createSelector, createSlice, PayloadAction} from "@reduxjs/toolkit";
import {RootState} from "../store";


export interface CoordInfo {
    x: number,
    y: number,
    r: number
}
const initialState: CoordInfo = {
    x: undefined,
    y: undefined,
    r: undefined
}

export const chooserSlice = createSlice({
    name: 'chooser',
    initialState,
    reducers: {
        setX: ((state, action: PayloadAction<number>) => {
            state.x = action.payload
        }),
        setY: ((state, action: PayloadAction<number>) => {
            state.y = action.payload
        }),
        setR: ((state, action: PayloadAction<number>) => {
            state.r = action.payload
        })
    }
})

export const selectIsRadiusSet = createSelector((state: CoordInfo) => state.r, item => (item==undefined || item==0));

export const {setX, setY, setR} = chooserSlice.actions

export default  chooserSlice.reducer