import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserdetailsService } from 'src/app/services/userdetails.service';

@Component({
  selector: 'app-adminviewuserdetails',
  templateUrl: './adminviewuserdetails.component.html',
  styleUrls: ['./adminviewuserdetails.component.css']
})
export class AdminviewuserdetailsComponent implements OnInit {

  constructor(private userservice:UserdetailsService) { }

  users:User[]=[]
  ngOnInit(): void {
   this.getAllUsers();

  }
  getAllUsers(){
    this.userservice.getAllUsers().subscribe(data=>{
     this.users=data;
    })
  }


     getUserbyName(name:string){
    this.userservice.getUserByName(name).subscribe((data)=>{
    this.users=data;

    })
  }

}
