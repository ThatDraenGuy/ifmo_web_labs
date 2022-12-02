import {api} from "./api";
import {CoordInfo} from "../slices/chooserSlice";
import {mapQuadrantsInfo} from "./quadrants";

export interface UserAttemptInfo {
    id: bigint,
    number: number,
    attemptInfo: AttemptInfo,
    userId: bigint,
    _links: {
        self: {
            "href": string
        }
    }
}

export interface AttemptInfo {
    shot: {
        res: boolean,
        message: string,
        execTime: string
    },
    coords: CoordInfo,
    currTime: string
}
interface UserAttemptInfoDtoList {
    _embedded: {
        userAttemptInfoDtoList: Array<UserAttemptInfo>
    }
}

export interface PageOfUserAttemptInfo {
    totalLength: bigint,
    pagesAmount: number,
    attempts: Array<UserAttemptInfo>,
}

const attemptApi = api.injectEndpoints({
    endpoints: build => ({
        shoot: build.mutation<UserAttemptInfo, CoordInfo>({
            query: (choice) => ({
                url: "/areacheck/shoot",
                method: "POST",
                body: choice,
            }),
            invalidatesTags: ["Attempts"]
        }),
        clear: build.mutation<any,void>({
            query: () => ({
                url: "/users/id/44/attempts/clear",
                method: "POST"
            }),
            invalidatesTags: ["Attempts"]
        }),
        attempts: build.query<Array<UserAttemptInfo>, void>({
            query: () => ({
                url: "/users/id/44/attempts",
                responseHandler: (response) => {
                    return response.text().then((value) => {
                        const dto: UserAttemptInfoDtoList = JSON.parse(value);
                        return dto._embedded.userAttemptInfoDtoList;
                    })
                }
            }),
            providesTags: ["Attempts"]
        }),
        attemptsPage: build.query<PageOfUserAttemptInfo, {page: number, size: number}>({
            query: ({page, size}) => ({
                url: `/users/id/44/attempts/page/${page}/${size}`
                // responseHandler: (response) => {
                //     return response.text().then((value) => {
                //         console.log(value);
                //         return null;
                //         // const dto: UserAttemptInfoDtoList = JSON.parse(value);
                //         // return dto._embedded.userAttemptInfoDtoList;
                //     })
                // }
            }),
            providesTags: ["Attempts"]
        })
    })
})

export const {useShootMutation, useClearMutation, useAttemptsQuery, useAttemptsPageQuery} = attemptApi