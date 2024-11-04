import { EventAssociations } from './EventAssociations';
import { Events } from './Events';
export interface EventsEventAssociations {
    eventAssociationId: number;
    eventId: number;
    events?: Events;
    eventAssociations?: EventAssociations;
}
