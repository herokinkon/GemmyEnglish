export class TimelineEvent {
    id: number;
    title: string;
    start: Date;
    end: Date;
    daysOfWeek: number[];
    startTime: Date;
    endTime: Date;
    startRecur: Date;
    endRecur: Date;
    assignee: string;
    class: string;
    description: string;
}