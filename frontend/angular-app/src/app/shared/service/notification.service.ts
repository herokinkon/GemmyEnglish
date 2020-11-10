import { Injectable } from '@angular/core'; import { Subject } from 'rxjs';
import { Message } from 'primeng/api/message';


@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  notification: Subject<Message> = new Subject();

  subcrible(callback: (m: Message) => void) {
    return this.notification.subscribe(message => {
      callback(message);
    });
  }

  addSuccessMessage(summary: string, detail: string) {
    const msg = { severity: 'success', summary, detail, closable: false };
    this.notification.next(msg);
  }

  addWarningMessage(summary: string, detail: string) {
    const msg = { severity: 'warn', summary, detail, sticky: true, closable: false };
    this.notification.next(msg);
  }

  addErrorMessage(summary: string, detail: string) {
    const msg = { severity: 'error', summary, detail, life: 5000, closable: false };
    this.notification.next(msg);
  }
}
