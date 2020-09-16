import { Staff } from '../../staff-management/staff-service/staff';

export class UserAccount {
    id: number;
    username: string;
    // Just for persistent
    password?: string;
    status: boolean;
    roles?: string;
    staff: Staff;
}