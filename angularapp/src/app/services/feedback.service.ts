import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Feedback } from '../models/feedback.model';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
 
@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
 
  private apiUrl=environment.apiUrl;
  constructor(private http:HttpClient) { }
 
  public addFeedback(feedback:Feedback):Observable<Feedback>{
    return this.http.post<Feedback>(`${this.apiUrl}/feedbacks`,feedback);
  }
 
  public getFeedbackByUserId(id:number):Observable<Feedback[]>{
    return this.http.get<Feedback[]>(`${this.apiUrl}/feedback/user/${id}`);
  }
 
  public deleteFeedbackById(id:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/feedback/${id}`);
  }
 
  public getAllFeedbacks():Observable<Feedback[]>{
    return this.http.get<Feedback[]>(`${this.apiUrl}/feedback`);
  }
 
  public getUserDetailById(id:number):Observable<User>{
    return this.http.get<User>(`${this.apiUrl}/user/${id}`);
  }
}
 