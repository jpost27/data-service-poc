import { Events } from './Events';
export interface EventStatuses {
    eventStatusCode: string;
    description?: string;
    events?: Events[];
}
