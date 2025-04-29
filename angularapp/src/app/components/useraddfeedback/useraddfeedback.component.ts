import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';
 
@Component({
  selector: 'app-useraddfeedback',
  templateUrl: './useraddfeedback.component.html',
  styleUrls: ['./useraddfeedback.component.css']
})
export class UseraddfeedbackComponent implements OnInit {
 
  newFeedback:Feedback={
    userId:localStorage.getItem('userId'),
    message:'',
    rating:0
  }
  constructor(private feedbackService:FeedbackService) { }
 
  ngOnInit(): void {
  }
 
  addFeedback():void{
    this.feedbackService.addFeedback(this.newFeedback).subscribe((data)=>{
      alert('feedback added successfully');
    })
  }
}
 