import {api} from "./api";
import {ChooserState} from "../slices/chooserSlice";


const shootApi = api.injectEndpoints({
    endpoints: build => ({
        shoot: build.mutation<any, ChooserState>({
            query: (choice) => ({
                url: "/areacheck/shoot",
                method: "POST",
                body: choice,
            })
        })
    })
})

export const {useShootMutation} = shootApi