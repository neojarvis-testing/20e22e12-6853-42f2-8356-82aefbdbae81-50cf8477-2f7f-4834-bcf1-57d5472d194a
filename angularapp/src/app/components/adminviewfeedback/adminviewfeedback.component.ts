import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { User } from 'src/app/models/user.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-adminviewfeedback',
  templateUrl: './adminviewfeedback.component.html',
  styleUrls: ['./adminviewfeedback.component.css']
})
export class AdminviewfeedbackComponent implements OnInit {

  feedbacks:Feedback[]=[];
  userDetail:User;
  userDetails:User[]=[]
  user:User
  userId:any
  constructor(private feedbackService:FeedbackService) { }

  ngOnInit(): void {
    this.getAllFeedbacks();
    this.userId=localStorage.getItem('userId');
    this.feedbackService.getUserDetailById(this.userId).subscribe((data)=>{
       this.user=data;
    });
  }

  getAllFeedbacks():void{
    this.feedbackService.getAllFeedbacks().subscribe((data)=>{
      this.feedbacks=data;
      this.getUserNameById();
    })
  }

  showProfile(id:number){
    this.feedbackService.getUserDetailById(id).subscribe((data)=>{
      this.userDetail=data;
      console.log(data);
    })
  }

  getUserNameById() {
    for (let feedback of this.feedbacks) {
      this.feedbackService.getUserDetailById(feedback.userId).subscribe((data) => {
        const enrichedFeedback = {
          ...feedback,
          username: data.username,
        };
        this.userDetails.push(enrichedFeedback);
      });
    }
    console.log(this.userDetails);
  }
}
