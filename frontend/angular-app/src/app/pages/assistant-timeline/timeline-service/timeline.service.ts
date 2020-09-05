import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AppConstant } from 'src/app/shared/app-constant.service';
import { TimelineEvent } from './timeline-event';
import { Observable } from 'rxjs';

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

  constructor(private http: HttpClient) { }

  getTimeline(id: number): Observable<TimelineEvent> {
    return this.http.get<TimelineEvent>(this.apiUrl + `${id}`, this.httpOptions);
  }

  deleteTimeline(id: number): Observable<TimelineEvent> {
    return this.http.delete<TimelineEvent>(this.apiUrl + `${id}`, this.httpOptions);
  }

  createTimeline(timeline: TimelineEvent): Observable<TimelineEvent> {
    return this.http.post<TimelineEvent>(this.apiUrl, timeline);
  }

  updateTimeline(timeline: TimelineEvent): Observable<TimelineEvent> {
    return this.http.put<TimelineEvent>(this.apiUrl, timeline, this.httpOptions);
  }

  searchTimeline(ids: any, startDate: Date, endDate: Date) {
    const data = { ...this.httpOptions, params: { startDate: startDate.toISOString(), endDate: endDate.toISOString() } };
    return this.http.post<TimelineEvent[]>(this.apiUrl + 'searchByStaffId', ids, data);
  }
}
