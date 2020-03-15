import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Output() toggleSideMenu = new EventEmitter<any>();
  title: string;
  userName: string;
  constructor() { }

  ngOnInit(): void {
    this.title = 'Gemmy English';
    this.userName = 'Administrator';
  }

}
