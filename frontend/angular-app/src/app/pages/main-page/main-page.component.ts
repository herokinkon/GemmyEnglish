import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { NotificationService } from 'src/app/shared/service/notification.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css'],
  providers: [MessageService]
})
export class MainPageComponent {
  title = 'angular-app';
  showFiller = false;

  constructor(private messageSer: MessageService, private notification: NotificationService) {
    this.notification.subcrible(msg => {
      this.messageSer.add(msg);
    });
  }
}
