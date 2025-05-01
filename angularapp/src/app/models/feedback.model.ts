
import { User } from "./user.model";


export interface Feedback{
    feedbackId?:number
    userId:any
    message:string
    rating:number
}