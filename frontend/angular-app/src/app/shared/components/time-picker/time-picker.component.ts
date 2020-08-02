import { Component, Input, Output, OnDestroy } from '@angular/core';
import { MatFormFieldControl } from '@angular/material/form-field';
import { Subject } from 'rxjs';
import { NgControl } from '@angular/forms';

@Component({
  selector: 'app-time-picker',
  templateUrl: './time-picker.component.html',
  styleUrls: ['./time-picker.component.css'],
  providers: [{ provide: MatFormFieldControl, useExisting: TimePickerComponent }]
})
export class TimePickerComponent implements MatFormFieldControl<Time>, OnDestroy {
  value: Time;
  stateChanges: Subject<void> = new Subject<void>();;
  id: string;
  placeholder: string;
  ngControl: NgControl;
  focused: boolean;
  empty: boolean;
  shouldLabelFloat: boolean;
  required: boolean;
  disabled: boolean;
  errorState: boolean;
  controlType?: string;
  autofilled?: boolean;

  @Input()
  minTime: Time;
  @Input()
  maxTime: Time;
  @Output()
  startD: Time;
  startDSuggestion: Time[];
  constructor() {
    // Business hours 09:00 -> 21:00
  }
  setDescribedByIds(ids: string[]): void {

  }
  onContainerClick(event: MouseEvent): void {

  }

  ngOnDestroy(): void {
    this.stateChanges.complete();
  }

}

export class Time {
  hours: number;
  minutes: number;
}
