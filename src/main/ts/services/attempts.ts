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

export const attemptApi = api.injectEndpoints({
    endpoints: build => ({
        shoot: build.mutation<UserAttempt, CoordInfo>({
            query: (choice) => ({
                url: "/areacheck/shoot",
                method: "POST",
                body: choice,
            }),
            invalidatesTags: ["Attempts"]
        }),
        clear: build.mutation<any,number>({
            query: (userId) => ({
                url: `/users/id/${userId}/attempts/clear`,
                method: "POST"
            }),
            invalidatesTags: ["Attempts"]
        }),
        attempts: build.query<Array<UserAttempt>, number>({
            query: (userId) => ({
                url: `/users/id/${userId}/attempts`,
                responseHandler: (response) => {
                    return response.text().then((value) => {
                        const dto: UserAttemptDtoList = JSON.parse(value);
                        return dto._embedded.userAttemptDtoList;
                    })
                }
            }),
            providesTags: ["Attempts"]
        }),
        attemptsPage: build.query<AttemptsPage, {userId: number, page: number, size: number}>({
            query: ({userId, page, size}) => ({
                url: `/users/id/${userId}/attempts/page/${page}/${size}`
            }),
            providesTags: ["Attempts"]
        })
    })
})

export const {useShootMutation, useClearMutation, useAttemptsQuery, useAttemptsPageQuery} = attemptApi