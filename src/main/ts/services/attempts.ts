import {api} from "./api";
import {CoordInfo} from "../slices/chooserSlice";

export interface UserAttempt {
    id: bigint,
    number: number,
    attempt: Attempt,
    userId: bigint,
    _links: {
        self: {
            "href": string
        }
    }
}

export interface Attempt {
    shot: {
        res: boolean,
        message: string,
        execTime: string
    },
    coords: CoordInfo,
    currTime: string
}
interface UserAttemptDtoList {
    _embedded: {
        userAttemptDtoList: Array<UserAttempt>
    }
}

export interface AttemptsPage {
    totalLength: bigint,
    pagesAmount: number,
    attempts: Array<UserAttempt>,
}

const attemptApi = api.injectEndpoints({
    endpoints: build => ({
        shoot: build.mutation<UserAttempt, CoordInfo>({
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
        attempts: build.query<Array<UserAttempt>, void>({
            query: () => ({
                url: "/users/id/44/attempts",
                responseHandler: (response) => {
                    return response.text().then((value) => {
                        const dto: UserAttemptDtoList = JSON.parse(value);
                        return dto._embedded.userAttemptDtoList;
                    })
                }
            }),
            providesTags: ["Attempts"]
        }),
        attemptsPage: build.query<AttemptsPage, {page: number, size: number}>({
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