import { Staff } from '../../staff-management/staff-service/staff';

export class TimelineEvent {
    id?: number;
    title?: string;
    start?: Date;
    end?: Date;
    daysOfWeek?: number[];
    startTime?: string;
    endTime?: string;
    startRecur?: Date;
    endRecur?: Date;
    staff?: Staff;
    description?: string;
}
