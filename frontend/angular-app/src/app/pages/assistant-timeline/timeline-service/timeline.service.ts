import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { TimelineEvent } from './timeline-event';
import { Observable } from 'rxjs';
import { NotificationService } from 'src/app/shared/service/notification.service';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TimelineService {

  apiUrl = AppConstant.serverURL + '/timeline/';
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient, private notification: NotificationService) { }

  getTimeline(id: number): Observable<TimelineEvent> {
    return this.http.get<TimelineEvent>(this.apiUrl + `${id}`, this.httpOptions);
  }

  deleteTimeline(id: number, title: string): Observable<TimelineEvent> {
    return this.http.delete<TimelineEvent>(this.apiUrl + `${id}`, this.httpOptions).pipe(
      tap(_ => this.notification.addSuccessMessage('Event', `[${title}] is deleted`)));
  }

  createTimeline(timeline: TimelineEvent): Observable<TimelineEvent> {
    return this.http.post<TimelineEvent>(this.apiUrl, timeline).pipe(
      tap(_ => this.notification.addSuccessMessage('Event', 'New timeline is created')));
  }

  updateTimeline(timeline: TimelineEvent): Observable<TimelineEvent> {
    return this.http.put<TimelineEvent>(this.apiUrl, timeline, this.httpOptions);
  }

  searchTimeline(ids: any, startDate: Date, endDate: Date) {
    const data = { ...this.httpOptions, params: { startDate: startDate.toISOString(), endDate: endDate.toISOString() } };
    return this.http.post<TimelineEvent[]>(this.apiUrl + 'searchByStaffId', ids, data).pipe(
      // tslint:disable-next-line:max-line-length
      tap(_ => this.notification.addSuccessMessage('', `Finished loading timeline from [${startDate.toDateString()}] to [${endDate.toDateString()}]`)));
  }
}
