import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Feedback } from '../models/feedback.model';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class FeedbackService {
// Define the base URL for the API, retrieved from environment configuration
private apiUrl: string = environment.apiUrl;

// Constructor: Inject HttpClient to perform HTTP operations
constructor(private http: HttpClient) {}

/**
 * Sends a POST request to create new feedback.
 * @param feedback - Feedback object to be created.
 * @returns Observable of the created Feedback object.
 */
createFeedback(feedback: Feedback): Observable<Feedback> {
   return this.http.post<Feedback>(`${this.apiUrl}/feedbacks`, feedback);
}

/**
 * Sends a GET request to fetch all feedback entries.
 * @returns Observable of an array containing all Feedback objects.
 */
getAllFeedback(): Observable<Feedback[]> {
  return this.http.get<Feedback[]>(`${this.apiUrl}/feedback`);
}

/**
 * Sends a PUT request to update a specific feedback entry by its ID.
 * @param feedbackId - ID of the feedback to be updated.
 * @param feedback - Updated Feedback object.
 * @returns Observable of the updated Feedback object.
 */
updateFeedback(feedbackId: number, feedback: Feedback): Observable<Feedback> {
  return this.http.put<Feedback>(`${this.apiUrl}/feedback/${feedbackId}`, feedback);
}

/**
 * Sends a DELETE request to delete a specific feedback entry by its ID.
 * @param feedbackId - ID of the feedback to be deleted.
 * @returns Observable<void> indicating operation completion.
 */
deleteFeedback(feedbackId: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/feedback/${feedbackId}`);
}

/**
 * Sends a GET request to fetch all feedback entries associated with a specific user.
 * @param userId - ID of the user whose feedback entries are being retrieved.
 * @returns Observable of an array containing Feedback objects related to the user.
 */
getFeedbackByUserId(userId: number): Observable<Feedback[]> {
  return this.http.get<Feedback[]>(`${this.apiUrl}/feedback/user/${userId}`);
}


}
